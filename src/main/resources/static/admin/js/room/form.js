function fileUploadCallback(files) {
    if (!files || files.length == 0) return;
        const mainImages = document.getElementById("main_images");
        const listImages = document.getElementById("list_images");
        const editorImages = document.getElementById("desc_images");

        const tpl = document.getElementById("tpl_image1").innerHTML;

        const domParser = new DOMParser();
        for (const file of files) {
            const location = file.location;
            let html = tpl;
            let target;
            html = html.replace(/\[id\]/g, file.id)
                        .replace(/\[url\]/g, file.thumbsUrl[0])
                        .replace(/\[orgUrl\]/g, file.fileUrl)
                        .replace(/\[fileName\]/g, file.fileName);
            const dom = domParser.parseFromString(html, "text/html");
            const el = dom.querySelector(".file_images");

            switch(location) {
                case "main" : // 메인 이미지
                    target = mainImages;
                    break;
                case "list" : // 목록 이미지
                    target = listImages;
                    break;
                case "desc" : // 상세설명 이미지
                    target = editorImages;
                    break;
            }

            if (target) target.appendChild(el);
        }
}


/**
* 파일 삭제 성공시 콜백 처리
*
* @param fileId : 파일 등록 번호
*/
function fileDeleteCallback(fileId) {
    if (!fileId) return;

    const el = document.getElementById(`file_${fileId}`);
    if (el) el.parentElement.removeChild(el);
}