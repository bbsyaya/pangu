package org.turing.pangu.service;

import java.util.ArrayList;

import org.turing.pangu.controller.common.PGResponse;
import org.turing.pangu.controller.common.Token;

public interface TokenService {

	/**
	 * 检查请求token
	 * @author turing
	 * @param token
	 * @param paramList
	 * @param type
	 * @return
	 */
	PGResponse<String> ValidateSignature(Token token, ArrayList<String> paramList, int type);

    String getOauthToken();
}
