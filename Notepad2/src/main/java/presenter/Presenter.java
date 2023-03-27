package presenter;

import model.ModelInterface;
import ui.View;

import java.util.List;

public class Presenter {
    private View view; //связываем через интерфейсы
    private ModelInterface modelInterface; //связываем через интерфейсы

    /**
     * Когда в презентер передадут как-то view мы у него вызовем метод setPresenter и передадим
     * текущий класс как класс в который он должен посылать сигнал
     * @param view
     * @param modelInterface
     */
    public Presenter(View view, ModelInterface modelInterface) {
        this.view = view;
        this.modelInterface = modelInterface;
        view.setPresenter(this);
    }

    /**
     * Этот метод должен обратиться к модели и передать комманду
     * @param commandNumber
     */
    public void getAnswer(String commandNumber) {
        String result = modelInterface.commandResult(commandNumber); // отправляет комманду в модель
        if (result.equals("Заметка создана")) {
            view.newNote();
        }
        if (result.equals("Выход")) {
            view.changeFlag();
        }
        view.print(result); //печатает результат работы модели по отправленной комманде
    }

    /**
     * Получение меню
     */
    public void getMenu() {
        String menu = modelInterface.createMenu(); //запрашивает меню у модуля
        view.print(menu); //отправляет полученое от модуля строку во view на печать
    }

    public void createNote(String newNote){
        modelInterface.createNewNote(newNote);
    }


    public boolean getFlag() {
        return modelInterface.exit();
    }
}
