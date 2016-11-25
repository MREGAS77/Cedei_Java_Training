package net.atos.cedei.cursospring.test.dbsetup;

import java.util.HashMap;
import java.util.Map;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import net.atos.cedei.cursospring.test.dbsetup.annotation.DBSetupDirty;

public abstract class DBSetupRule implements TestRule {

    public static final String DEFAULT_INSTANCE = DBSetupRule.class.getName() + ".INSTANCE";
    private static final Map<String, Boolean> instancesInitialized = new HashMap<>();

    private String instance;

    public DBSetupRule() {
        this(DEFAULT_INSTANCE);
    }

    public DBSetupRule(String instance) {
        this.instance = instance;

        if (instancesInitialized.get(instance) == null) {
            instancesInitialized.put(instance, Boolean.FALSE);
        }
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return statement(base, description);
    }

    public void dirty() {
        instancesInitialized.put(instance, Boolean.FALSE);
    }

    public void cleaned() {
        instancesInitialized.put(instance, Boolean.TRUE);
    }

    private Statement statement(final Statement base, final Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                if (Boolean.FALSE.equals(instancesInitialized.get(instance))) {
                    refresh();
                    cleaned();
                }
                try {
                    base.evaluate();
                } finally {
                    if (description.getAnnotation(DBSetupDirty.class) != null) {
                        dirty();
                    }
                }
            }
        };
    }

    /**
     * Refresh database
     */
    protected abstract void refresh();

}