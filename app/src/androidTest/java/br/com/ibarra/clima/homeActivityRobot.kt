package br.com.ibarra.clima

import br.com.concretesolutions.kappuccino.actions.ClickActions.click
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import br.com.concretesolutions.kappuccino.custom.intent.IntentMatcherInteractions.sentIntent
import br.com.ibarra.clima.ui.activities.ConfigurationActivity

fun homeActivity(func: HomeActivityRobot.() -> Unit) = HomeActivityRobot().apply(func)

class HomeActivityRobot {

    fun weatherFieldsAreVisible() {
        displayed {
            id(R.id.temperature)
            id(R.id.unit)
            id(R.id.weatherImage)
        }
    }

    fun currentCityIs(city: String) {
        displayed {
            allOf {
                parent(R.id.toolbar){
                    text(city)
                }

            }
        }
    }

    infix fun clickFormatButton(func: HomeActivityResult.() -> Unit) {
        click {
            id(R.id.action_settings)
        }

        HomeActivityResult().apply(func)
    }
}

class HomeActivityResult {
    fun redirectToNextActivity() {
        sentIntent {
            className(ConfigurationActivity::class.java.name)
        }
    }
}