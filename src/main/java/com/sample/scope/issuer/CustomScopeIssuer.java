package com.sample.scope.issuer;


import org.wso2.carbon.apimgt.keymgt.ScopesIssuer;
import org.wso2.carbon.identity.oauth2.token.OAuthTokenReqMessageContext;


/**
 *  Custom Scope Issuer implementation for wso2 apim 3.0
 */
public class CustomScopeIssuer extends ScopesIssuer {


    @Override
    public boolean setScopes(OAuthTokenReqMessageContext tokReqMsgCtx) {

        String callBackUrl = tokReqMsgCtx.getOauth2AccessTokenReqDTO().getCallbackURI();

        // Engage the default scope issuer for publisher and devportal apps
        if (callBackUrl != null && (callBackUrl.contains("devportal") || callBackUrl.contains("publisher"))) {
            return super.setScopes(tokReqMsgCtx);
        }
        String[] scopes = new String[]{"scope_1", "scope_2"};
        tokReqMsgCtx.setScope(scopes);
        return true;
    }

}
