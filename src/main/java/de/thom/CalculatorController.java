package de.thom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CalculatorController {
     
	@Autowired
     private Calculator calculator;

     @RequestMapping(method=RequestMethod.GET, value="/sum")
     String sum(@RequestParam("a") Integer a, 
                @RequestParam("b") Integer b) {
          return String.valueOf(calculator.sum(a, b));
     }
     
     @RequestMapping(method=RequestMethod.GET, value="/healthz")
 	 public String healthCheck()
 	 {
 		return "OK";
 	 }
}
