package gazua.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBooking is a Querydsl query type for Booking
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBooking extends EntityPathBase<Booking> {

    private static final long serialVersionUID = -1540543666L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBooking booking = new QBooking("booking");

    public final StringPath bo_num = createString("bo_num");

    public final StringPath bu_title = createString("bu_title");

    public final DateTimePath<java.time.LocalDateTime> checkin = createDateTime("checkin", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> checkout = createDateTime("checkout", java.time.LocalDateTime.class);

    public final QMember member;

    public final StringPath payment = createString("payment");

    public final StringPath price = createString("price");

    public final StringPath reg_date = createString("reg_date");

    public final StringPath ro_name = createString("ro_name");

    public final StringPath ro_num = createString("ro_num");

    public final StringPath status = createString("status");

    public QBooking(String variable) {
        this(Booking.class, forVariable(variable), INITS);
    }

    public QBooking(Path<? extends Booking> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBooking(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBooking(PathMetadata metadata, PathInits inits) {
        this(Booking.class, metadata, inits);
    }

    public QBooking(Class<? extends Booking> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

