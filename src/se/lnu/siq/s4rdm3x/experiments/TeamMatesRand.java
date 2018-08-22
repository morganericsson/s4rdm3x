package se.lnu.siq.s4rdm3x.experiments;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import se.lnu.siq.s4rdm3x.cmd.HuGMe;
import se.lnu.siq.s4rdm3x.cmd.LoadJar;
import se.lnu.siq.s4rdm3x.cmd.Selector;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

import static java.nio.file.Files.*;

public class TeamMatesRand extends ExperimentRunner {

    public TeamMatesRand() {
        super("TeamMates1");
    }


    @Override
    protected void assignMetric(Graph a_g, HuGMe.ArchDef a_arch) {

        for(Node n : a_g.getEachNode()) {
            if (a_arch.getMappedComponent(n) != null) {
                setMetric(n, m_rand.nextDouble());
            }
        }
    }



    private HuGMe.ArchDef.Component createAddAndMapComponent(Graph a_g, HuGMe.ArchDef a_ad, String a_componentName, String a_package) {
        HuGMe.ArchDef.Component c = a_ad.addComponent(a_componentName);
        c.mapToNodes(a_g, new Selector.Pkg(a_package));
        return c;
    }

    @Override
    protected HuGMe.ArchDef createAndMapArch(Graph a_g) {
        HuGMe.ArchDef ad = new HuGMe.ArchDef();

        HuGMe.ArchDef.Component commonUtil = createAddAndMapComponent(a_g, ad, "common.util","teammates/common/util/");
        HuGMe.ArchDef.Component commonException = createAddAndMapComponent(a_g, ad,"common.exception", "teammates/common/exception/");
        HuGMe.ArchDef.Component commonDataTransfer = createAddAndMapComponent(a_g, ad,"common.datatransfer", "teammates/common/datatransfer/");

        HuGMe.ArchDef.Component uiAutomated = createAddAndMapComponent(a_g, ad,"ui.automated","teammates/ui/automated/");
        HuGMe.ArchDef.Component uiController = createAddAndMapComponent(a_g, ad,"ui.controller","teammates/ui/controller/");
        HuGMe.ArchDef.Component uiView = createAddAndMapComponent(a_g, ad, "ui.view", new String[]{"teammates/ui/datatransfer/", "teammates/ui/pagedata/", "teammates/ui/template/"});

        HuGMe.ArchDef.Component logicCore = createAddAndMapComponent(a_g, ad,"logic.core", "teammates/logic/core/");
        HuGMe.ArchDef.Component logicApi = createAddAndMapComponent(a_g, ad,"logic.api", "teammates/logic/api/");
        HuGMe.ArchDef.Component logicBackdoor = createAddAndMapComponent(a_g, ad, "logic.backdoor", "teammates/logic/backdoor/");

        HuGMe.ArchDef.Component storageEntity = createAddAndMapComponent(a_g, ad,"storage.entity", "teammates/storage/entity/");
        HuGMe.ArchDef.Component storageApi = createAddAndMapComponent(a_g, ad,"storage.api", "teammates/storage/api/");
        HuGMe.ArchDef.Component storageSearch = createAddAndMapComponent(a_g, ad,"storage.search", "teammates/storage/search/");

        uiAutomated.addDependencyTo(commonUtil);
        uiAutomated.addDependencyTo(commonException);
        uiAutomated.addDependencyTo(commonDataTransfer);
        uiAutomated.addDependencyTo(logicApi);
        uiController.addDependencyTo(commonUtil);
        uiController.addDependencyTo(commonException);
        uiController.addDependencyTo(commonDataTransfer);
        uiController.addDependencyTo(logicApi);
        uiAutomated.addDependencyTo(commonUtil);
        uiAutomated.addDependencyTo(commonException);
        uiAutomated.addDependencyTo(commonDataTransfer);
        uiView.addDependencyTo(commonUtil);
        uiView.addDependencyTo(commonException);
        uiView.addDependencyTo(commonDataTransfer);

        logicCore.addDependencyTo(commonUtil);
        logicCore.addDependencyTo(commonException);
        logicCore.addDependencyTo(commonDataTransfer);
        logicCore.addDependencyTo(storageApi);
        logicApi.addDependencyTo(commonUtil);
        logicApi.addDependencyTo(commonException);
        logicApi.addDependencyTo(commonDataTransfer);
        logicBackdoor.addDependencyTo(commonUtil);
        logicBackdoor.addDependencyTo(commonException);
        logicBackdoor.addDependencyTo(commonDataTransfer);
        logicBackdoor.addDependencyTo(storageApi);

        storageEntity.addDependencyTo(commonUtil);
        storageEntity.addDependencyTo(commonException);
        storageEntity.addDependencyTo(commonDataTransfer);
        storageApi.addDependencyTo(commonUtil);
        storageApi.addDependencyTo(commonException);
        storageApi.addDependencyTo(commonDataTransfer);
        storageSearch.addDependencyTo(commonUtil);
        storageSearch.addDependencyTo(commonException);
        storageSearch.addDependencyTo(commonDataTransfer);

        commonDataTransfer.addDependencyTo(storageEntity);



        return ad;
    }

    @Override
    protected boolean load(Graph a_g) {
        LoadJar c = new LoadJar("data/teammatesV5.110.jar", "");
        try {
            c.run(a_g);
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
