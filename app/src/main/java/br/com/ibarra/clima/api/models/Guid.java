package br.com.ibarra.clima.api.models;

import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

/**
 * Created by joaoibarra on 19/01/16.
 */
@TableModelSpec(className="Guid", tableName="guids", tableConstraint = "FOREIGN KEY(itemId) REFERENCES items(_id)")
public class Guid {
    @PrimaryKey
    long id;
    long itemId;
    private boolean isPermanentLink;
    private String content;
}
