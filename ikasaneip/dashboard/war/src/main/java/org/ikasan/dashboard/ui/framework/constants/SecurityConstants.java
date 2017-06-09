/*
 * $Id$  
 * $URL$
 * 
 * ====================================================================
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
 * ====================================================================
 */
package org.ikasan.dashboard.ui.framework.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ikasan Development Team
 *
 */
public class SecurityConstants
{

	public static String ALL_AUTHORITY = "ALL";

	// New policies
	public static String WIRETAP_READ = "wiretap-read";
	public static String WIRETAP_WRITE = "wiretap-write";
	public static String WIRETAP_ADMIN = "wiretap-admin";

	public static String ERROR_READ = "error-read";
	public static String ERROR_WRITE = "error-write";
	public static String ERROR_ADMIN = "error-admin";

	public static String ACTIONED_ERROR_READ = "actioned_error-read";
	public static String ACTIONED_ERROR_WRITE = "actioned_error-write";
	public static String ACTIONED_ERROR_ADMIN = "actioned_error-admin";

	public static String CATEGORISED_ERROR_READ = "categorised_error-read";
	public static String CATEGORISED_ERROR_WRITE = "categorised_error-write";
	public static String CATEGORISED_ERROR_ADMIN = "categorised_error-admin";

	public static String EXCLUSION_READ = "exclusion-read";
	public static String EXCLUSION_WRITE = "exclusion-write";
	public static String EXCLUSION_ADMIN = "exclusion-admin";

	public static String ACTIONED_EXCLUSION_READ = "actioned_exclusion-read";
	public static String ACTIONED_EXCLUSION_WRITE = "actioned_exclusion-write";
	public static String ACTIONED_EXCLUSION_ADMIN = "actioned_exclusion-admin";

	public static String SYSTEM_EVENT_READ = "system_event-read";
	public static String SYSTEM_EVENT_WRITE = "system_event-write";
	public static String SYSTEM_EVENT_ADMIN = "system_event-admin";

	public static String FILTER_READ = "filter-read";
	public static String FILTER_WRITE = "filter-write";
	public static String FILTER_ADMIN = "filter-admin";

	public static String TOPOLOGY_READ = "topology-read";
	public static String TOPOLOGY_WRITE = "topology-write";
	public static String TOPOLOGY_ADMIN = "topology-admin";

	public static String MAPPING_READ = "mapping-read";
	public static String MAPPING_WRITE = "mapping-write";
	public static String MAPPING_ADMIN = "mapping-admin";

	public static String MONITORING_READ = "monitoring-read";
	public static String MONITORING_WRITE = "monitoring-write";
	public static String MONITORING_ADMIN = "monitoring-admin";

	public static String REPLAY_READ = "replay-read";
	public static String REPLAY_WRITE = "replay-write";
	public static String REPLAY_ADMIN = "replay-admin";

	public static String HOUSEKEEPING_READ = "housekeeping-read";
	public static String HOUSEKEEPING_WRITE = "housekeeping-write";
	public static String HOUSEKEEPING_ADMIN = "housekeeping-admin";

	public static String USER_ADMINISTRATION_READ = "user_administration-read";
	public static String USER_ADMINISTRATION_WRITE = "user_administration-write";
	public static String USER_ADMINISTRATION_ADMIN = "user_administration-admin";

	public static String GROUP_ADMINISTRATION_READ = "group_administration-read";
	public static String GROUP_ADMINISTRATION_WRITE = "group_administration-write";
	public static String GROUP_ADMINISTRATION_ADMIN = "group_administration-admin";

	public static String ROLE_ADMINISTRATION_READ = "role_administration-read";
	public static String ROLE_ADMINISTRATION_WRITE = "role_administration-write";
	public static String ROLE_ADMINISTRATION_ADMIN = "role_administration-admin";

	public static String POLICY_ADMINISTRATION_READ = "policy_administration-read";
	public static String POLICY_ADMINISTRATION_WRITE = "policy_administration-write";
	public static String POLICY_ADMINISTRATION_ADMIN = "policy_administration-admin";

	public static String USER_DIRECTORY_READ = "user_directory-read";
	public static String USER_DIRECTORY_WRITE = "user_directory-write";
	public static String USER_DIRECTORY_ADMIN = "user_directory-admin";

	public static String PLATORM_CONFIGURATON_READ = "platform_configuration-read";
	public static String PLATORM_CONFIGURATON_WRITE = "platform_configuration-write";
	public static String PLATORM_CONFIGURATON_ADMIN = "platform_configuration-admin";

	public static String NOTIFICATION_READ = "notification-read";
	public static String NOTIFICATION_WRITE = "notification-write";
	public static String NOTIFICATION_ADMIN = "notification-admin";

	public static List<String> SERVICE_VIEW_PERMISSIONS;

	public static List<String> TOPOLOGY_VIEW_PERMISSIONS;

	public static List<String> MAPPING_VIEW_PERMISSIONS;

	public static List<String> MONITORING_VIEW_PERMISSIONS;

	public static List<String> HOUSEKEEPING_VIEW_PERMISSIONS;

	public static List<String> REPLAY_VIEW_PERMISSIONS;

	public static List<String> ADMINISTRATION_VIEW_PERMISSIONS;

	public static List<String> USER_ADMINISTRATION_VIEW_PERMISSIONS;

	static
	{
		SERVICE_VIEW_PERMISSIONS = new ArrayList<String>();

		SERVICE_VIEW_PERMISSIONS.add(WIRETAP_READ);
		SERVICE_VIEW_PERMISSIONS.add(WIRETAP_WRITE);
		SERVICE_VIEW_PERMISSIONS.add(WIRETAP_ADMIN);
		SERVICE_VIEW_PERMISSIONS.add(ERROR_READ);
		SERVICE_VIEW_PERMISSIONS.add(ERROR_WRITE);
		SERVICE_VIEW_PERMISSIONS.add(ERROR_ADMIN);
		SERVICE_VIEW_PERMISSIONS.add(ACTIONED_ERROR_READ);
		SERVICE_VIEW_PERMISSIONS.add(ACTIONED_ERROR_WRITE);
		SERVICE_VIEW_PERMISSIONS.add(ACTIONED_ERROR_ADMIN);
		SERVICE_VIEW_PERMISSIONS.add(CATEGORISED_ERROR_READ);
		SERVICE_VIEW_PERMISSIONS.add(CATEGORISED_ERROR_WRITE);
		SERVICE_VIEW_PERMISSIONS.add(CATEGORISED_ERROR_ADMIN);
		SERVICE_VIEW_PERMISSIONS.add(EXCLUSION_READ);
		SERVICE_VIEW_PERMISSIONS.add(EXCLUSION_WRITE);
		SERVICE_VIEW_PERMISSIONS.add(EXCLUSION_ADMIN);
		SERVICE_VIEW_PERMISSIONS.add(ACTIONED_EXCLUSION_READ);
		SERVICE_VIEW_PERMISSIONS.add(ACTIONED_EXCLUSION_WRITE);
		SERVICE_VIEW_PERMISSIONS.add(ACTIONED_EXCLUSION_ADMIN);
		SERVICE_VIEW_PERMISSIONS.add(SYSTEM_EVENT_READ);
		SERVICE_VIEW_PERMISSIONS.add(SYSTEM_EVENT_WRITE);
		SERVICE_VIEW_PERMISSIONS.add(SYSTEM_EVENT_ADMIN);
		SERVICE_VIEW_PERMISSIONS.add(FILTER_READ);
		SERVICE_VIEW_PERMISSIONS.add(FILTER_WRITE);
		SERVICE_VIEW_PERMISSIONS.add(FILTER_ADMIN);
		SERVICE_VIEW_PERMISSIONS.add(TOPOLOGY_READ);
		SERVICE_VIEW_PERMISSIONS.add(TOPOLOGY_WRITE);
		SERVICE_VIEW_PERMISSIONS.add(TOPOLOGY_ADMIN);
		SERVICE_VIEW_PERMISSIONS.add(MAPPING_ADMIN);
		SERVICE_VIEW_PERMISSIONS.add(MAPPING_READ);
		SERVICE_VIEW_PERMISSIONS.add(MAPPING_WRITE);
		SERVICE_VIEW_PERMISSIONS.add(REPLAY_ADMIN);
		SERVICE_VIEW_PERMISSIONS.add(REPLAY_READ);
		SERVICE_VIEW_PERMISSIONS.add(REPLAY_WRITE);
		SERVICE_VIEW_PERMISSIONS.add(MONITORING_ADMIN);
		SERVICE_VIEW_PERMISSIONS.add(MONITORING_READ);
		SERVICE_VIEW_PERMISSIONS.add(MONITORING_WRITE);
		SERVICE_VIEW_PERMISSIONS.add(HOUSEKEEPING_ADMIN);
		SERVICE_VIEW_PERMISSIONS.add(HOUSEKEEPING_READ);
		SERVICE_VIEW_PERMISSIONS.add(HOUSEKEEPING_WRITE);

		TOPOLOGY_VIEW_PERMISSIONS = new ArrayList<String>();
		TOPOLOGY_VIEW_PERMISSIONS.add(WIRETAP_READ);
		TOPOLOGY_VIEW_PERMISSIONS.add(WIRETAP_WRITE);
		TOPOLOGY_VIEW_PERMISSIONS.add(WIRETAP_ADMIN);
		TOPOLOGY_VIEW_PERMISSIONS.add(ERROR_READ);
		TOPOLOGY_VIEW_PERMISSIONS.add(ERROR_WRITE);
		TOPOLOGY_VIEW_PERMISSIONS.add(ERROR_ADMIN);
		TOPOLOGY_VIEW_PERMISSIONS.add(ACTIONED_ERROR_READ);
		TOPOLOGY_VIEW_PERMISSIONS.add(ACTIONED_ERROR_WRITE);
		TOPOLOGY_VIEW_PERMISSIONS.add(ACTIONED_ERROR_ADMIN);
		TOPOLOGY_VIEW_PERMISSIONS.add(CATEGORISED_ERROR_READ);
		TOPOLOGY_VIEW_PERMISSIONS.add(CATEGORISED_ERROR_WRITE);
		TOPOLOGY_VIEW_PERMISSIONS.add(CATEGORISED_ERROR_ADMIN);
		TOPOLOGY_VIEW_PERMISSIONS.add(EXCLUSION_READ);
		TOPOLOGY_VIEW_PERMISSIONS.add(EXCLUSION_WRITE);
		TOPOLOGY_VIEW_PERMISSIONS.add(EXCLUSION_ADMIN);
		TOPOLOGY_VIEW_PERMISSIONS.add(ACTIONED_EXCLUSION_READ);
		TOPOLOGY_VIEW_PERMISSIONS.add(ACTIONED_EXCLUSION_WRITE);
		TOPOLOGY_VIEW_PERMISSIONS.add(ACTIONED_EXCLUSION_ADMIN);
		TOPOLOGY_VIEW_PERMISSIONS.add(SYSTEM_EVENT_READ);
		TOPOLOGY_VIEW_PERMISSIONS.add(SYSTEM_EVENT_WRITE);
		TOPOLOGY_VIEW_PERMISSIONS.add(SYSTEM_EVENT_ADMIN);
		TOPOLOGY_VIEW_PERMISSIONS.add(FILTER_READ);
		TOPOLOGY_VIEW_PERMISSIONS.add(FILTER_WRITE);
		TOPOLOGY_VIEW_PERMISSIONS.add(FILTER_ADMIN);
		TOPOLOGY_VIEW_PERMISSIONS.add(TOPOLOGY_READ);
		TOPOLOGY_VIEW_PERMISSIONS.add(TOPOLOGY_WRITE);
		TOPOLOGY_VIEW_PERMISSIONS.add(TOPOLOGY_ADMIN);

		MAPPING_VIEW_PERMISSIONS = new ArrayList<String>();

		MAPPING_VIEW_PERMISSIONS.add(MAPPING_ADMIN);
		MAPPING_VIEW_PERMISSIONS.add(MAPPING_READ);
		MAPPING_VIEW_PERMISSIONS.add(MAPPING_WRITE);

		MONITORING_VIEW_PERMISSIONS = new ArrayList<String>();

		MONITORING_VIEW_PERMISSIONS.add(MONITORING_WRITE);
		MONITORING_VIEW_PERMISSIONS.add(MONITORING_READ);
		MONITORING_VIEW_PERMISSIONS.add(MONITORING_ADMIN);

		HOUSEKEEPING_VIEW_PERMISSIONS = new ArrayList<String>();

		HOUSEKEEPING_VIEW_PERMISSIONS.add(HOUSEKEEPING_WRITE);
		HOUSEKEEPING_VIEW_PERMISSIONS.add(HOUSEKEEPING_READ);
		HOUSEKEEPING_VIEW_PERMISSIONS.add(HOUSEKEEPING_ADMIN);

		REPLAY_VIEW_PERMISSIONS = new ArrayList<String>();

		REPLAY_VIEW_PERMISSIONS.add(REPLAY_READ);
		REPLAY_VIEW_PERMISSIONS.add(REPLAY_WRITE);
		REPLAY_VIEW_PERMISSIONS.add(REPLAY_ADMIN);


		ADMINISTRATION_VIEW_PERMISSIONS = new ArrayList<String>();

		ADMINISTRATION_VIEW_PERMISSIONS.add(USER_ADMINISTRATION_WRITE);
		ADMINISTRATION_VIEW_PERMISSIONS.add(USER_ADMINISTRATION_ADMIN);
		ADMINISTRATION_VIEW_PERMISSIONS.add(GROUP_ADMINISTRATION_WRITE);
		ADMINISTRATION_VIEW_PERMISSIONS.add(GROUP_ADMINISTRATION_ADMIN);
		ADMINISTRATION_VIEW_PERMISSIONS.add(ROLE_ADMINISTRATION_WRITE);
		ADMINISTRATION_VIEW_PERMISSIONS.add(ROLE_ADMINISTRATION_ADMIN);
		ADMINISTRATION_VIEW_PERMISSIONS.add(POLICY_ADMINISTRATION_WRITE);
		ADMINISTRATION_VIEW_PERMISSIONS.add(POLICY_ADMINISTRATION_ADMIN);
		ADMINISTRATION_VIEW_PERMISSIONS.add(USER_DIRECTORY_WRITE);
		ADMINISTRATION_VIEW_PERMISSIONS.add(USER_DIRECTORY_ADMIN);
		ADMINISTRATION_VIEW_PERMISSIONS.add(PLATORM_CONFIGURATON_WRITE);
		ADMINISTRATION_VIEW_PERMISSIONS.add(PLATORM_CONFIGURATON_ADMIN);
		ADMINISTRATION_VIEW_PERMISSIONS.add(NOTIFICATION_WRITE);
		ADMINISTRATION_VIEW_PERMISSIONS.add(NOTIFICATION_ADMIN);

		USER_ADMINISTRATION_VIEW_PERMISSIONS = new ArrayList<String>();

		USER_ADMINISTRATION_VIEW_PERMISSIONS.add(USER_ADMINISTRATION_WRITE);
		USER_ADMINISTRATION_VIEW_PERMISSIONS.add(USER_ADMINISTRATION_ADMIN);
	}
}
