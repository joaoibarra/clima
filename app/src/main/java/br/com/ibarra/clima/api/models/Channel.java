package br.com.ibarra.clima.api.models;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by joaoibarra on 19/01/16.
 */
public class Channel extends SugarRecord{
    private Item item;

    public Item getItem() {
        return item;
    }

    @Override
    public long save() {
        this.deleteAll(Channel.class);
        return super.save();
    }
}
