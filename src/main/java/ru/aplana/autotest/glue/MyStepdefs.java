package ru.aplana.autotest.glue;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import ru.aplana.autotest.steps.IpotekaPageSteps;
import ru.aplana.autotest.steps.MainPageSteps;
import static ru.aplana.autotest.steps.BaseSteps.*;

public class MyStepdefs {

    private MainPageSteps mainPageSteps = new MainPageSteps();

    private IpotekaPageSteps ipotekaPageSteps = new IpotekaPageSteps();

    @Before
    public void before(){
        initDriver();
    }

    @Когда("^выбрано меню Ипотека$")
    public void выбрано_меню_Ипотека(){
        mainPageSteps.selectMenu();
    }

    @Когда("^выбран вид ипотеки Ипотека на готовое жильё$")
    public void выбран_вид_ипотеки_Ипотека_на_готовое_жильё(){
        mainPageSteps.selectItemMenu();
    }

    @Когда("^выполнен скроллинг до формы расчета ипотеки$")
    public void выполнен_скроллинг_до_формы_расчета_ипотеки(){
        ipotekaPageSteps.scrollToFrame();
    }

    @Когда("^выполнено переключение на форму расчета ипотеки$")
    public void выполнено_переключение_на_форму_расчета_ипотеки(){
        ipotekaPageSteps.switchFrame();
    }

    @Когда("^заполняются поля:$")
    public void заполняются_поля(DataTable fields){
            fields.asMap(String.class, String.class)
                    .forEach((field, value) -> {
                        ipotekaPageSteps.fillField(field, value);
                    });
    }

    @Когда("^выполнено нажатие на кнопку \"Молодая семья\"$")
    public void нажатие_на_кнопку_Молодая_семья() throws InterruptedException {
        ipotekaPageSteps.clickOnInvisibleElement("Молодая семья");
    }

    @Когда("^выполнено нажатие на кнопку \"Есть зарплатная карта Сбербанка\"$")
    public void нажатие_на_кнопку_Зарплатная_карта() throws InterruptedException {
        ipotekaPageSteps.clickOnInvisibleElement("Есть зарплатная карта Сбербанка");
    }

    @Тогда("^значения полей равны:$")
    public void значения_полей(DataTable fields) throws Exception {
        fields.asMap(String.class, String.class)
                .forEach((field, value) -> { new IpotekaPageSteps().checkCalcValue(field, value); });
    }

    @After
    public void after() throws Exception {
        tearDown();
    }
}