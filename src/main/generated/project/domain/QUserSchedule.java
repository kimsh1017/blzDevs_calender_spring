package project.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserSchedule is a Querydsl query type for UserSchedule
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserSchedule extends EntityPathBase<UserSchedule> {

    private static final long serialVersionUID = 137083447L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserSchedule userSchedule = new QUserSchedule("userSchedule");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSchedule schedule;

    public final QUser user;

    public QUserSchedule(String variable) {
        this(UserSchedule.class, forVariable(variable), INITS);
    }

    public QUserSchedule(Path<? extends UserSchedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserSchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserSchedule(PathMetadata metadata, PathInits inits) {
        this(UserSchedule.class, metadata, inits);
    }

    public QUserSchedule(Class<? extends UserSchedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.schedule = inits.isInitialized("schedule") ? new QSchedule(forProperty("schedule"), inits.get("schedule")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

