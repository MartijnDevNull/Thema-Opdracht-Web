/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/

package atd.securityfilters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import atd.domein.AccountWrapper;
import atd.domein.Klant;
import atd.domein.Privilege;

public class SecurityFilterKlant implements Filter {
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		/* Filter is being placed into service, do nothing. */
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest r2 = (HttpServletRequest) req;
		HttpServletResponse httpResponse = (HttpServletResponse) resp;
		AccountWrapper gebruiker = (AccountWrapper) r2.getSession().getAttribute("username");
		if (r2.getSession().getAttribute("username") == null || gebruiker.getPriv() == Privilege.MONTEUR) {
			r2.setAttribute("redirect", r2.getRequestURI());
			r2.getRequestDispatcher("/login/forbidden.jsp").forward(req, resp);
			return;
		} else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {
		/* Filter is being taken out of service, do nothing. */
	}
}