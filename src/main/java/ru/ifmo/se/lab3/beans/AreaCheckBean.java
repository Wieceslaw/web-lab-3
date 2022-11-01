package ru.ifmo.se.lab3.beans;

import lombok.*;
import ru.ifmo.se.lab3.entities.Result;
import ru.ifmo.se.lab3.model.Repository;
import ru.ifmo.se.lab3.services.CheckService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@ManagedBean(name="areaCheck")
@SessionScoped
@Getter
@Setter
@NoArgsConstructor
public class AreaCheckBean implements Serializable {
    @ManagedProperty(value = "#{repository}")
    private Repository repository;
    private Double x;
    private Double y;
    private Double r = 1d;

    public void hit() {
        System.out.println(getCookie("timezoneoffset").getValue());
        long startTime = System.nanoTime();
        Result result = new Result(
                System.currentTimeMillis() / 1000L,
                (System.nanoTime() - startTime),
                x,
                y,
                r,
                CheckService.check(x, y, r)
        );
        repository.createWithTransaction(result);
    }

    public Cookie getCookie(String name) {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        Cookie cookie = null;

        Cookie[] userCookies = request.getCookies();
        if (userCookies != null && userCookies.length > 0 ) {
            for (int i = 0; i < userCookies.length; i++) {
                if (userCookies[i].getName().equals(name)) {
                    cookie = userCookies[i];
                    return cookie;
                }
            }
        }
        return null;
    }
}
