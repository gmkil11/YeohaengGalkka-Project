package gazua.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRoom is a Querydsl query type for Room
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoom extends EntityPathBase<Room> {

    private static final long serialVersionUID = -413775354L;

    public static final QRoom room = new QRoom("room");

    public final BooleanPath active = createBoolean("active");

    public final StringPath gid = createString("gid");

    public final StringPath roomCount = createString("roomCount");

    public final StringPath roomInfo1 = createString("roomInfo1");

    public final StringPath roomInfo2 = createString("roomInfo2");

    public final StringPath roomInfo3 = createString("roomInfo3");

    public final StringPath roomName = createString("roomName");

    public final NumberPath<Long> roomNum = createNumber("roomNum", Long.class);

    public final StringPath roomPr = createString("roomPr");

    public final StringPath sellerId = createString("sellerId");

    public QRoom(String variable) {
        super(Room.class, forVariable(variable));
    }

    public QRoom(Path<? extends Room> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoom(PathMetadata metadata) {
        super(Room.class, metadata);
    }

}

