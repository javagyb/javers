package org.javers.core.json.typeadapter.change;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import org.javers.core.commit.CommitMetadata;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.metamodel.type.TypeMapper;

import java.util.Optional;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

class ValueChangeTypeAdapter extends ChangeTypeAdapter<ValueChange> {
    private static final String LEFT_VALUE_FIELD = "left";
    private static final String RIGHT_VALUE_FIELD = "right";

    public ValueChangeTypeAdapter(TypeMapper typeMapper) {
        super(typeMapper);
    }

    @Override
    public ValueChange fromJson(JsonElement json, JsonDeserializationContext context) {
        JsonObject jsonObject = (JsonObject) json;
        PropertyChangeStub stub = deserializeStub(jsonObject, context);

        Object leftValue  = context.deserialize(jsonObject.get(LEFT_VALUE_FIELD),  stub.property.getGenericType());
        Object rightValue = context.deserialize(jsonObject.get(RIGHT_VALUE_FIELD), stub.property.getGenericType());

        CommitMetadata commitMetadata = deserializeCommitMetadata(jsonObject, context);
        return new ValueChange(stub.id, stub.getPropertyName(), leftValue, rightValue, ofNullable(commitMetadata));
    }

    @Override
    public JsonElement toJson(ValueChange change, JsonSerializationContext context) {
        JsonObject jsonObject = createJsonObject(change, context);

        jsonObject.add(LEFT_VALUE_FIELD, context.serialize(change.getLeft()));
        jsonObject.add(RIGHT_VALUE_FIELD, context.serialize(change.getRight()));

        return jsonObject;
    }

    @Override
    public Class getValueType() {
        return ValueChange.class;
    }
}
