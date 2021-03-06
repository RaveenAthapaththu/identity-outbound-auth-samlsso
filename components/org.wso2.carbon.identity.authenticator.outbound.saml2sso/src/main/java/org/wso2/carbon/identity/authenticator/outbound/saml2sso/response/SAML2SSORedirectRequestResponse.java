/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.authenticator.outbound.saml2sso.response;

import org.opensaml.xml.security.x509.X509Credential;
import org.wso2.carbon.identity.auth.saml2.common.SAML2AuthUtils;

/**
 * The AuthnRequest sent over redirect binding to the federated IdP.
 */
public class SAML2SSORedirectRequestResponse extends SAML2SSORequestResponse {

    private static final long serialVersionUID = 8147926229380700336L;

    protected boolean isAuthnRequestSigned;
    protected transient X509Credential idPCredential = null;
    protected String sigAlg;

    protected SAML2SSORedirectRequestResponse(
            SAML2SSORedirectRequestResponse.SAML2SSORedirectRequestResponseBuilder builder) {
        super(builder);
        isAuthnRequestSigned = builder.isAuthnRequestSigned;
        idPCredential = builder.idPCredential;
        sigAlg = builder.sigAlg;
    }

    public boolean isAuthnRequestSigned() {
        return isAuthnRequestSigned;
    }

    public X509Credential getIdPCredential() {
        if (idPCredential == null) {
            idPCredential = SAML2AuthUtils.getServerCredentials();
        }
        return idPCredential;
    }

    public String getSigAlg() {
        return sigAlg;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SAML2SSORedirectRequestResponse{");
        sb.append("isAuthnRequestSigned=").append(isAuthnRequestSigned);
        sb.append(", sigAlg='").append(sigAlg).append('\'');
        sb.append(", authnRequestSigned=").append(isAuthnRequestSigned());
        sb.append('}');
        return sb.toString();
    }

    /**
     * The builder for building SAML2SSORedirectRequestResponse.
     */
    public static class SAML2SSORedirectRequestResponseBuilder extends SAML2SSORequestResponseBuilder {

        protected boolean isAuthnRequestSigned;
        protected X509Credential idPCredential;
        protected String sigAlg;

        public SAML2SSORedirectRequestResponseBuilder setAuthnRequestSigned(boolean isAuthnRequestSigned) {
            this.isAuthnRequestSigned = isAuthnRequestSigned;
            return this;
        }

        public SAML2SSORedirectRequestResponseBuilder setIdPCredential(X509Credential idPCredential) {
            this.idPCredential = idPCredential;
            return this;
        }

        public SAML2SSORedirectRequestResponseBuilder setSigAlg(String sigAlg) {
            this.sigAlg = sigAlg;
            return this;
        }

        public SAML2SSORedirectRequestResponse build() {
            return new SAML2SSORedirectRequestResponse(this);
        }
    }
}
