package com.progeduc.service;

import com.progeduc.model.EmailBody;

public interface EmailPort {
	public boolean sendEmail(EmailBody emailBody);
}