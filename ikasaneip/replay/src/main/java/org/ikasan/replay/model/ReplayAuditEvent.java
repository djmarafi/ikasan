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
package org.ikasan.replay.model;

import org.ikasan.spec.replay.ReplayEvent;

/**
 * 
 * @author Ikasan Development Team
 *
 */
public class ReplayAuditEvent
{
	private ReplayAuditEventKey id;
	private ReplayAudit replayAudit;
	private ReplayEvent replayEvent;
	private boolean success;
	private String resultMessage;
	private long timestamp;
   
    
    @SuppressWarnings("unused")
	private ReplayAuditEvent()
    {
    }

    
    /**
     * Constructor
     * @param replayAudit
     * @param replayEvent
     */
	public ReplayAuditEvent(ReplayAudit replayAudit, ReplayEvent replayEvent, boolean success, String result, long timestamp)
	{
		super();
		this.replayAudit = replayAudit;
		if(this.replayAudit == null)
		{
			throw new IllegalArgumentException("ReplayAudit cannot be null!!");
		}
		this.replayEvent = replayEvent;
		if(this.replayEvent == null)
		{
			throw new IllegalArgumentException("ReplayEvent cannot be null!!");
		}
		
		this.id = new ReplayAuditEventKey(replayAudit.getId(), replayEvent.getId());
		this.resultMessage = result;
		this.success = success;
		this.timestamp = timestamp;
	}


	/**
	 * @return the id
	 */
	public ReplayAuditEventKey getId() 
	{
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(ReplayAuditEventKey id) 
	{
		this.id = id;
	}


	/**
	 * @return the replayAudit
	 */
	public ReplayAudit getReplayAudit()
	{
		return replayAudit;
	}


	/**
	 * @param replayAudit the replayAudit to set
	 */
	public void setReplayAudit(ReplayAudit replayAudit)
	{
		this.replayAudit = replayAudit;
	}
	

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}


	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}


	/**
	 * @return the resultMessage
	 */
	public String getResultMessage() {
		return resultMessage;
	}


	/**
	 * @param resultMessage the resultMessage to set
	 */
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}


	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
