package org.apache.zeppelin.cep;


/**
 * Created by minudika on 10/4/17.
 */

import org.apache.zeppelin.interpreter.Interpreter;
import org.apache.zeppelin.interpreter.InterpreterContext;
import org.apache.zeppelin.interpreter.InterpreterResult;

import java.util.Properties;

public class ReceiverInterpreter extends Interpreter {

    public ReceiverInterpreter(Properties property) {
        super(property);
    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public InterpreterResult interpret(String st, InterpreterContext context) {

        return new InterpreterResult(InterpreterResult.Code.SUCCESS);
    }

    @Override
    public void cancel(InterpreterContext context) {

    }

    @Override
    public FormType getFormType() {
        return FormType.NATIVE;
    }

    @Override
    public int getProgress(InterpreterContext context) {
        return 0;
    }
}
