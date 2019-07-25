package me.itaot.payment.router.service.impl;

import me.itaot.payment.router.beans.FormatInfo;
import me.itaot.payment.router.beans.RouteInfo;
import me.itaot.payment.router.service.RouteService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 充值路由
 */
@Component
public class RechargeRouteService implements RouteService {

    @Override
    public List<RouteInfo> route(List<FormatInfo> formatInfoList, int tranAmt, String routeMethod) {
        return null;
    }
}
