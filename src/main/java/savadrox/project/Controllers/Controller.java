package savadrox.project.Controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import savadrox.project.Repositories.RepositoryEngine;
import savadrox.project.Repositories.RepositoryPojazdElektryczny;
import savadrox.project.Repositories.RepositoryPojazdSpalinowy;
import savadrox.project.SaveToFile;

import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {
    public RepositoryPojazdElektryczny repositoryPojazdElektryczny;
    public RepositoryPojazdSpalinowy repositoryPojazdSpalinowy;
    public RepositoryEngine repositoryEngine;

    public Controller(RepositoryPojazdElektryczny repositoryPojazdElektryczny, RepositoryPojazdSpalinowy repositoryPojazdSpalinowy, RepositoryEngine repositoryEngine) {
        this.repositoryPojazdElektryczny = repositoryPojazdElektryczny;
        this.repositoryPojazdSpalinowy = repositoryPojazdSpalinowy;
        this.repositoryEngine = repositoryEngine;
    }
    SaveToFile zapis = new SaveToFile();
    @RequestMapping("/pojazdy/spalinowe")
    public String getPojazdySpalinowe(Model pojazdSpalinowy,Model engine){
        pojazdSpalinowy.addAttribute("pojazdySpalinowe",repositoryPojazdSpalinowy.findAll());

        pojazdSpalinowy.addAttribute("engines",repositoryEngine.findAll());
        return "pojazdySpalinowe";
    }
    @RequestMapping("/pojazdy/elektryczne")
    public String getPojazdyElektryczne(Model pojazdElektryczny){
        pojazdElektryczny.addAttribute("pojazdyElektryczne",repositoryPojazdElektryczny.findAll());
        return "pojazdyElektryczne";
    }
    @RequestMapping("/zapis")
    public void zapis(@RequestParam(value="value", required=false, defaultValue="World") String value) throws IOException {
        zapis.saveTables(repositoryPojazdElektryczny.findAll(),repositoryPojazdSpalinowy.findAll());
    }
    @RequestMapping("/odczyt")
    public void odczyt(@RequestParam(value="value", required=false, defaultValue="World") String value) throws IOException {
        zapis.loadTables(repositoryPojazdElektryczny,repositoryPojazdSpalinowy,repositoryEngine);
    }


}
