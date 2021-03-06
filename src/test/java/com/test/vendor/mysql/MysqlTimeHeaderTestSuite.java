package com.test.vendor.mysql;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.jsql.model.InjectionModel;
import com.jsql.model.MediatorModel;
import com.jsql.model.exception.JSqlException;
import com.jsql.model.injection.method.MethodInjection;
import com.jsql.model.injection.strategy.StrategyInjection;
import com.jsql.util.ConnectionUtil;
import com.jsql.util.ParameterUtil;
import com.jsql.util.PreferencesUtil;
import com.jsql.view.terminal.SystemOutTerminal;
import com.test.AbstractTestSuite;

public class MysqlTimeHeaderTestSuite extends ConcreteMysqlTestSuite {

    @BeforeClass
    public static void initialize() throws Exception {
        InjectionModel model = new InjectionModel();
        MediatorModel.register(model);
        model.displayVersion();

        MediatorModel.model().addObserver(new SystemOutTerminal());

        PreferencesUtil.setNotTestingConnection(true);
        
        ParameterUtil.initQueryString("http://"+ AbstractTestSuite.HOSTNAME +"/simulate_header.php");
        ParameterUtil.initRequest("");
        ParameterUtil.initHeader("lib: 1*");
        ConnectionUtil.setMethodInjection(MethodInjection.HEADER);
        ConnectionUtil.setTypeRequest("POST");
        
        MediatorModel.model().beginInjection();

        MediatorModel.model().setStrategy(StrategyInjection.TIME);
    }

    @Override
    @Test
    @Ignore
    public void listDatabases() throws JSqlException {
        LOGGER.info("Ignore: too slow");
    }

    @Override
    @Test
    @Ignore
    public void listTables() throws JSqlException {
        LOGGER.info("Ignore: too slow");
    }
    
}
