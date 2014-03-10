package org.javers.core.metamodel.property;

import static org.javers.common.validation.Validate.argumentIsNotNull;

/**
 * @author bartosz walacik
 */
public class EntityDefinition  extends ManagedClassDefinition {
    private final String idPropertyName;

    /**
     * gives you Entity with id property selected on the basis of @Id annotation
     */
    public EntityDefinition(Class<?> clazz) {
        super(clazz);
        this.idPropertyName = null;
    }

    /**
     * gives you Entity with id property selected explicitly by name
     */
    public EntityDefinition(Class<?> clazz, String idPropertyName) {
        super(clazz);
        argumentIsNotNull(idPropertyName);
        this.idPropertyName = idPropertyName;
    }

    public boolean hasCustomId() {
        return idPropertyName != null;
    }

    public String getIdPropertyName() {
        return idPropertyName;
    }
}
