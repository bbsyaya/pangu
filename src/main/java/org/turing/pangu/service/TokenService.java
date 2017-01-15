package org.turing.pangu.service;

import java.util.ArrayList;

import org.turing.pangu.controller.phone.MobileResponse;
import org.turing.pangu.controller.phone.Token;

public interface TokenService {

	/**
	 * 检查请求token
	 * @author turing
	 * @param token
	 * @param paramList
	 * @param type
	 * @return
	 */
	MobileResponse<String> ValidateSignature(Token token, ArrayList<String> paramList, int type);

    String getOauthToken();
}
