package br.com.ibarra.clima.database;

import android.content.Context;

import com.yahoo.squidb.data.SquidDatabase;
import com.yahoo.squidb.data.adapter.SQLiteDatabaseWrapper;
import com.yahoo.squidb.sql.Table;

import br.com.ibarra.clima.api.models.Channel;
import br.com.ibarra.clima.api.models.ChannelSpec;
import br.com.ibarra.clima.api.models.Condition;
import br.com.ibarra.clima.api.models.ConditionSpec;
import br.com.ibarra.clima.api.models.ForecastSpec;
import br.com.ibarra.clima.api.models.GuidSpec;
import br.com.ibarra.clima.api.models.ItemSpec;
import br.com.ibarra.clima.api.models.ResultSpec;
import br.com.ibarra.clima.api.models.Weather;
import br.com.ibarra.clima.api.models.WeatherSpec;

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
                ForecastSpec.TABLE,
                GuidSpec.TABLE,
                ItemSpec.TABLE,
                ResultSpec.TABLE,
                WeatherSpec.TABLE
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

    public void saveAll(ClimaDatabase climaDatabase, Weather weather){
        WeatherSpec weatherSpec = new WeatherSpec().
        climaDatabase.persist(weather);
    }
}
