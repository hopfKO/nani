package nani.domain.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    public String execute(String input) {
        return String.format("[%s]がサービスにわたりました。", input);
    };
}
