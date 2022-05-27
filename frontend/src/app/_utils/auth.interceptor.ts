import { HTTP_INTERCEPTORS, HttpEvent } from "@angular/common/http";
import { Injectable } from "@angular/core";
import {
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
} from "@angular/common/http";
import { TokenService } from "../_services/token.service";
import { Observable } from "rxjs";

const TOKEN_HEADER = "Authorization";

export class AuthInterceptor implements HttpInterceptor {
  constructor(private token: TokenService) {}

  // gets HttpRequest object, changes and fowards it to HttpHandler object’s handle() method. It transforms the HttpRequest object into an Observable<HttpEvents>.
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.token.getToken();
    if (token != null) {
      authReq = req.clone({
        headers: req.headers.set(TOKEN_HEADER, "Bearer " + token),
      });
    }
    return next.handle(authReq);
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
];
