package project.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorkspace is a Querydsl query type for Workspace
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWorkspace extends EntityPathBase<Workspace> {

    private static final long serialVersionUID = -1316741632L;

    public static final QWorkspace workspace = new QWorkspace("workspace");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<UserWorkspace, QUserWorkspace> userWorkspaces = this.<UserWorkspace, QUserWorkspace>createList("userWorkspaces", UserWorkspace.class, QUserWorkspace.class, PathInits.DIRECT2);

    public QWorkspace(String variable) {
        super(Workspace.class, forVariable(variable));
    }

    public QWorkspace(Path<? extends Workspace> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorkspace(PathMetadata metadata) {
        super(Workspace.class, metadata);
    }

}

