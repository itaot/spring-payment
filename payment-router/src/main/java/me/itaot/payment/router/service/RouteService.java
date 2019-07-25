package me.itaot.payment.router.service;

import me.itaot.payment.router.beans.FormatInfo;
import me.itaot.payment.router.beans.RouteInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RouteService {

    public static final String MIN_FEE = "0";

    public List<RouteInfo> route(List<FormatInfo> formatInfoList, int tranAmt,String routeMethod);

}
