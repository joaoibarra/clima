package br.com.ibarra.clima.database;

import android.content.Context;

import com.yahoo.squidb.data.SquidDatabase;
import com.yahoo.squidb.data.adapter.SQLiteDatabaseWrapper;
import com.yahoo.squidb.sql.Table;

import br.com.ibarra.clima.api.models.ChannelSpec;
import br.com.ibarra.clima.api.models.ConditionSpec;
import br.com.ibarra.clima.api.models.Forecast;
import br.com.ibarra.clima.api.models.Guid;
import br.com.ibarra.clima.api.models.Item;
import br.com.ibarra.clima.api.models.Result;
import br.com.ibarra.clima.api.models.Weather;

/**
 * Created by joaoibarra on 1/21/16.
 */
public class ClimaDatabase extends SquidDatabase{
    private static final int VERSION = 1;

    public ClimaDatabase(Context context) {
        super(context);
    }

    @Override
    public String getName() {
        return "clima.db";
    }

    @Override
    protected Table[] getTables() {
        return new Table[]{
                // List all tables here
                ChannelSpec.TABLE,
                ConditionSpec.TABLE,
                Forecast.TABLE,
                Guid.TABLE,
                Item.TABLE,
                Result.TABLE,
                Weather.TABLE
        };
    }

    @Override
    protected int getVersion() {
        return VERSION;
    }

    @Override
    protected boolean onUpgrade(SQLiteDatabaseWrapper sqLiteDatabaseWrapper, int i, int i1) {
        return false;
    }
}
