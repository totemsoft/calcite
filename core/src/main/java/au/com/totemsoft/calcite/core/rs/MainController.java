package au.com.totemsoft.calcite.core.rs;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    value = "/"
    , consumes = {MediaType.APPLICATION_JSON_VALUE}
    , produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class MainController {

//    @RequestMapping(path="/process", method=RequestMethod.POST)
//    public MainResponse process(@RequestBody MainRequest request) {
//        return null;
//    }

}
