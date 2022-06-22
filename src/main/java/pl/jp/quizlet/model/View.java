package pl.jp.quizlet.model;

public class View {
    //Enclosing type to define User views
    public static interface UserView {
        //External View for User
        public static interface External {
        }
        //Intenal View for User, will inherit all filds in External
        public static interface Internal extends External {
        }
    }
}

