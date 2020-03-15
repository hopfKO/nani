package nani.app.welcome;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.ibatis.io.ResolverUtil.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nani.domain.service.HelloService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HelloController {

    @Inject
    HelloService helloService;

    private final String TEST_STR = "Hello?";

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);

        model.addAttribute("test", helloService.execute(TEST_STR));

        return "welcome/home";
    }

    /**
     * 
     */
    @RequestMapping(value = "/api/{id}", method = { RequestMethod.GET })
    @ResponseBody
    public Anime apiGet(@PathVariable("id") String id, Model model) {
        List<Anime> animes = new ArrayList<>();
        Anime inuyasha = new Anime();
        inuyasha.setId("1");
        inuyasha.setTitle("犬夜叉");
        inuyasha.setAirDate(LocalDate.now());

        animes.add(inuyasha);
        
        Optional<Anime> oAnime = animes.stream().filter(anime -> Objects.equals(anime.getId(), id)).findFirst();

        return oAnime.orElse(null);
    }

}
