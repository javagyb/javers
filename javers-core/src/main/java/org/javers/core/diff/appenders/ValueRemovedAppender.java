package org.javers.core.diff.appenders;

import org.javers.core.diff.NodePair;
import org.javers.core.diff.changetype.ValueRemoved;
import org.javers.model.mapping.Property;
import org.javers.model.mapping.type.CollectionType;
import org.javers.model.mapping.type.JaversType;

import java.util.Collection;
import java.util.Set;

/**
 * @author bartosz walacik
 */
public class ValueRemovedAppender extends PropertyChangeAppender<ValueRemoved> {

    //TODO in fact it should be Collection<PrimitiveOrValueType>
    @Override
    protected Class<? extends JaversType> getSupportedPropertyType() {
        return CollectionType.class;
    }
    @Override
    public Collection<ValueRemoved> calculateChanges(NodePair pair, Property supportedProperty) {
        throw new IllegalStateException("not implemented");
    }
}