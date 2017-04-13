package org.apache.zeppelin.cep;


/**
 * Created by minudika on 10/4/17.
 */

import org.apache.zeppelin.cep.beans.EventProcessor;
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
        EventProcessor eventProcessor = new EventProcessor();
        String html = "<html>" +
                "<H!>Minudika Malshan</H1>" +
                "</html>";
        try {
            eventProcessor.run(st.split(" "));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new InterpreterResult(InterpreterResult.Code.SUCCESS, InterpreterResult.Type.HTML,html);
    }

    @Override
    public void cancel(InterpreterContext context) {

    }

    @Override
    public FormType getFormType() {
        return FormType.SIMPLE;
    }

    @Override
    public int getProgress(InterpreterContext context) {
        return 0;
    }
}
