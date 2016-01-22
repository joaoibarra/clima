package br.com.ibarra.clima.api.models;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by joaoibarra on 19/01/16.
 */
public class Guid extends SugarRecord {
    private boolean isPermanentLink;
    private String content;
}
