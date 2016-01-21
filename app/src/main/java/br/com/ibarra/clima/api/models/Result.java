package br.com.ibarra.clima.api.models;


import com.yahoo.squidb.annotations.PrimaryKey;
import com.yahoo.squidb.annotations.TableModelSpec;

/**
 * Created by joaoibarra on 19/01/16.
 */
@TableModelSpec(className="Result", tableName="results", tableConstraint = "FOREIGN KEY(weatherId) REFERENCES weathers(_id)")
public class Result {
    @PrimaryKey
    long id;
    long weatherId;
    private ChannelSpec channel;

    public ChannelSpec getChannel() {
        return channel;
    }
}
