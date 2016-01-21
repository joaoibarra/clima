package br.com.ibarra.clima.api.models;

import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

/**
 * Created by joaoibarra on 19/01/16.
 */
@TableModelSpec(className="Channel", tableName="channels", tableConstraint = "FOREIGN KEY(resultId) REFERENCES results(_id)")
public class ChannelSpec {
    @PrimaryKey
    long id;
    long resultId;

    private Item item;

    public Item getItem() {
        return item;
    }
}
