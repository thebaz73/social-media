package crossover.social.media.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Setting
 * Created by bazzoni
 */
@Document
@CompoundIndexes({
        @CompoundIndex(name = "userId_key_idx", def = "{'userId' : 1, 'key' : 1}", unique = true)
})
public class Setting {
    @Id
    private String id;

    private String key;
    private SettingType type;
    private Object value;

    public Setting() {
    }

    @PersistenceConstructor
    public Setting(String key, Object value, SettingType type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SettingType getType() {
        return type;
    }

    public void setType(SettingType type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
