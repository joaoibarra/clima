package br.com.ibarra.clima.api.models;


import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by joaoibarra on 19/01/16.
 */
public class Result extends SugarRecord {
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    @Override
    public long save() {
        this.deleteAll(Result.class);
        return super.save();
    }
}
