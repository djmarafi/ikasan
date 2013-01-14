/*
 * $Id$
 * $URL$
 * 
 * =============================================================================
 * Ikasan Enterprise Integration Platform
 * 
 * Distributed under the Modified BSD License.
 * Copyright notice: The copyright for this software and a full listing 
 * of individual contributors are as shown in the packaged copyright.txt 
 * file. 
 * 
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 *  - Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer.
 *
 *  - Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution.
 *
 *  - Neither the name of the ORGANIZATION nor the names of its contributors may
 *    be used to endorse or promote products derived from this software without 
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE 
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * =============================================================================
 */
package org.ikasan.common.security;

import java.util.HashMap;
import java.util.Map;

/**
 * A factory class to create the AuthenticationPolicy instances.
 *
 * TODO - make this a singleton
 *
 * @author Ikasan Development Team
 */
public class AuthenticationPolicyFactory
{
    /** Constructor */
    public AuthenticationPolicyFactory()
    {
        // Do Nothing
    }
    
    /**
     * Get the new instance of the AuthenticationPolicy
     * 
     * @param options - The options to use 
     * @return AuthenticationPolicy
     */
    public static AuthenticationPolicy getAuthenticationPolicy(final Map<String, String> options)
    {
        return new AuthenticationPolicyImpl(options);
    }

    /**
     * Get the new instance of the AuthenticationPolicy
     * 
     * @param policyName - The policy name to use
     * @param encryptionPoliciesURL - The encryption policies URL
     * @return AuthenticationPolicy
     */
    public static AuthenticationPolicy getAuthenticationPolicy(final String policyName, 
            final String encryptionPoliciesURL)
    {
        Map<String,String> map = new HashMap<String,String>();
        map.put(AuthenticationPolicyConst.POLICY_NAME_LITERAL, policyName);
        map.put(AuthenticationPolicyConst.ENCRYPTION_POLICIES_URL_LITERAL, encryptionPoliciesURL);
        return new AuthenticationPolicyImpl(map);
    }
    
    /**
     * Get the new instance of the AuthenticationPolicy
     * 
     * @param policyName - the policy name to use
     * @return AuthenticationPolicy
     */
    public static AuthenticationPolicy getAuthenticationPolicy(final String policyName)
    {
        return getAuthenticationPolicy(policyName, null);
    }    
}