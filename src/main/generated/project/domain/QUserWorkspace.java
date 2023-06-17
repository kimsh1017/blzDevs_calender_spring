package project.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserWorkspace is a Querydsl query type for UserWorkspace
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserWorkspace extends EntityPathBase<UserWorkspace> {

    private static final long serialVersionUID = 1224194293L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserWorkspace userWorkspace = new QUserWorkspace("userWorkspace");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser user;

    public final QWorkspace workspace;

    public QUserWorkspace(String variable) {
        this(UserWorkspace.class, forVariable(variable), INITS);
    }

    public QUserWorkspace(Path<? extends UserWorkspace> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserWorkspace(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserWorkspace(PathMetadata metadata, PathInits inits) {
        this(UserWorkspace.class, metadata, inits);
    }

    public QUserWorkspace(Class<? extends UserWorkspace> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
        this.workspace = inits.isInitialized("workspace") ? new QWorkspace(forProperty("workspace")) : null;
    }

}

