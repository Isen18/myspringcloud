package com.isen.resttemplate.server;

import com.isen.common.dto.FooDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Isen
 * @date 2018/9/17 23:43
 * @since 1.0
 */
@RestController
@RequestMapping("/foos")
public class FooController {

    private static List<FooDTO> foos = new ArrayList<>();

    static {
        for(int i = 0; i < 5; i++){
            foos.add(new FooDTO(Long.valueOf(i), "foo_" + i, i + 1, i));
        }
    }

    @RequestMapping
    public List<FooDTO> list(){
        return foos;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public FooDTO get(@PathVariable("id") Long id){
        return foos.stream().filter(foo -> foo.getId().equals(id)).collect(Collectors.toList()).get(0);
    }

    @RequestMapping(value = "/post/postForObject", method = RequestMethod.POST)
    public FooDTO postForObject(@RequestBody FooDTO fooDTO){
        System.out.println("recieve a fooDTO=" +  fooDTO);
        return fooDTO;
    }

    @RequestMapping(value = "/post/postForLocation", method = RequestMethod.POST)
    public String postForLocation(@RequestBody FooDTO fooDTO){
        System.out.println("recieve a fooDTO=" +  fooDTO);
        return "新资源路径uri";
    }

    @RequestMapping(value = "/post/exchange", method = RequestMethod.POST)
    public FooDTO exchange(@RequestBody FooDTO fooDTO){
        System.out.println("recieve a fooDTO=" +  fooDTO);
        return fooDTO;
    }

    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    public FooDTO put(@RequestBody FooDTO fooDTO){
        System.out.println("recieve a fooDTO=" +  fooDTO);
        fooDTO.setName("updated：name");
        return fooDTO;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id){
        System.out.println("delete a fooDTO[id=" + id + "]");
    }
}
