/*
 * Copyright (c) 2020 Seagate Technology LLC and/or its Affiliates
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For any questions about this software or licensing,
 * please email opensource@seagate.com or cortx-questions@seagate.com.
 *
 */

#include "s3_action_base.h"
#include "s3_api_handler.h"
#include "s3_account_delete_metadata_action.h"
#include "s3_get_audit_log_schema_action.h"

void S3ManagementAPIHandler::create_action() {
  s3_log(S3_LOG_DEBUG, request_id, "%s Entry", __func__);
  s3_log(S3_LOG_DEBUG, request_id, "Action operation code = %d\n",
         operation_code);
  switch (operation_code) {
    case S3OperationCode::none:
      // Perform operation on Service.
      switch (request->http_verb()) {
        case S3HttpVerb::DELETE: {
          action = std::make_shared<S3AccountDeleteMetadataAction>(request);
          s3_log(S3_LOG_DEBUG, request_id, "S3AccountDeleteMetadataAction");
        } break;
        case S3HttpVerb::GET: {
          std::string full_uri(request->c_get_full_path());
          if (full_uri.compare("/s3/audit-log/schema") == 0) {
            action = std::make_shared<S3GetAuditLogSchemaAction>(request);
            s3_log(S3_LOG_DEBUG, request_id, "S3GetAuditLogSchemaAction");
          }
        } break;
        default:
          // should never be here.
          return;
      };
      break;
    default:
      // should never be here.
      return;
  };  // switch operation_code
  s3_log(S3_LOG_DEBUG, request_id, "%s Exit", __func__);
}
