package gazua.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QConfigs is a Querydsl query type for Configs
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConfigs extends EntityPathBase<Configs> {

    private static final long serialVersionUID = -654112666L;

    public static final QConfigs configs = new QConfigs("configs");

    public final StringPath code = createString("code");

    public final StringPath value = createString("value");

    public QConfigs(String variable) {
        super(Configs.class, forVariable(variable));
    }

    public QConfigs(Path<? extends Configs> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConfigs(PathMetadata metadata) {
        super(Configs.class, metadata);
    }

}

