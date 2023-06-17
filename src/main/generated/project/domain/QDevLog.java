package project.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDevLog is a Querydsl query type for DevLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDevLog extends EntityPathBase<DevLog> {

    private static final long serialVersionUID = 2053304676L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDevLog devLog = new QDevLog("devLog");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSchedule schedule;

    public final QUser user;

    public QDevLog(String variable) {
        this(DevLog.class, forVariable(variable), INITS);
    }

    public QDevLog(Path<? extends DevLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDevLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDevLog(PathMetadata metadata, PathInits inits) {
        this(DevLog.class, metadata, inits);
    }

    public QDevLog(Class<? extends DevLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.schedule = inits.isInitialized("schedule") ? new QSchedule(forProperty("schedule"), inits.get("schedule")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

