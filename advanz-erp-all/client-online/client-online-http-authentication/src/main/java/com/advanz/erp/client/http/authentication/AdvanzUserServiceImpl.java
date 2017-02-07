package com.advanz.erp.client.http.authentication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContextException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import com.advanz.erp.client.http.authentication.util.UserMenuOptions;

public class AdvanzUserServiceImpl extends JdbcDaoSupport
implements UserDetailsService
{
public static final String DEF_USERS_BY_USERNAME_QUERY = "select login_user,user_password,active_flag from m_user where login_user = ?";
public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = "select u.login_user , rm.role_name from m_role_master rm , m_user u, m_user_role ur where rm.role_id = ur.role_id and u.user_id = ur.user_id and u.login_user = ?";
public static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY = "select g.id, g.group_name, ga.authority from groups g, group_members gm, group_authorities ga where gm.username = ? and g.id = ga.group_id and g.id = gm.group_id";
public static final String DEF_MENU_OPTIONS_BY_ROLE_QUERY = "SELECT mmm.module_name , mmm.sub_module_name, mmm.menu_name,rr.visible_flag, rr.add_flag, rr.edit_flag, rr.delete_flag from m_module_menu_master mmm, m_role_rights rr, m_role_master rm where mmm.menu_id = rr.menu_id and rm.role_id = rr.role_id and rm.role_name = ? order by mmm.module_name ASC, mmm.sub_module_name ASC, mmm.menu_name ASC";

protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
private String authoritiesByUsernameQuery;
private String groupAuthoritiesByUsernameQuery;	
private String usersByUsernameQuery;
private String userMenuOptionsQuery;
private String rolePrefix = "";
private boolean usernameBasedPrimaryKey = true;
private boolean enableAuthorities = true;
private boolean enableGroups;

public AdvanzUserServiceImpl()
{
  this.usersByUsernameQuery = DEF_USERS_BY_USERNAME_QUERY;
  this.authoritiesByUsernameQuery = DEF_AUTHORITIES_BY_USERNAME_QUERY;
  this.groupAuthoritiesByUsernameQuery = DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY;
  this.userMenuOptionsQuery = DEF_MENU_OPTIONS_BY_ROLE_QUERY;
}

protected void addCustomAuthorities(String username, List<GrantedAuthority> authorities)
{
}

public String getUsersByUsernameQuery()
{
  return this.usersByUsernameQuery;
}

protected void initDao() throws ApplicationContextException {
  Assert.isTrue((this.enableAuthorities) || (this.enableGroups), "Use of either authorities or groups must be enabled");
}

public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  List users = loadUsersByUsername(username);

  if (users.size() == 0) {
    this.logger.debug("Query returned no results for user '" + username + "'");

    throw new UsernameNotFoundException(this.messages.getMessage("JdbcDaoImpl.notFound", new Object[] { username }, "Username {0} not found"), username);
  }

  UserDetails user = (UserDetails)users.get(0);

  Set dbAuthsSet = new HashSet();

  if (this.enableAuthorities) {
    dbAuthsSet.addAll(loadUserAuthorities(user.getUsername()));
  }

  if (this.enableGroups) {
    dbAuthsSet.addAll(loadGroupAuthorities(user.getUsername()));
  }

  List dbAuths = new ArrayList(dbAuthsSet);

  addCustomAuthorities(user.getUsername(), dbAuths);

  if (dbAuths.size() == 0) {
    this.logger.debug("User '" + username + "' has no authorities and will be treated as 'not found'");

    throw new UsernameNotFoundException(this.messages.getMessage("JdbcDaoImpl.noAuthority", new Object[] { username }, "User {0} has no GrantedAuthority"), username);
  }

  return createUserDetails(username, user, dbAuths);
}

protected List<UserDetails> loadUsersByUsername(String username)
{
  return getJdbcTemplate().query(this.usersByUsernameQuery, new String[] { username }, new RowMapper() {
    public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
      String username = rs.getString(1);
      String password = rs.getString(2);
      boolean enabled = rs.getBoolean(3);
      return new User(username, password, enabled, true, true, true, AuthorityUtils.NO_AUTHORITIES);
    }
  });
}

protected List<GrantedAuthority> loadUserAuthorities(String username)
{
  return getJdbcTemplate().query(this.authoritiesByUsernameQuery, new String[] { username }, new RowMapper() {
    public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
      String roleName = AdvanzUserServiceImpl.this.rolePrefix + rs.getString(2);

      return new SimpleGrantedAuthority(roleName);
    }
  });
}

protected List<GrantedAuthority> loadGroupAuthorities(String username)
{
  return getJdbcTemplate().query(this.groupAuthoritiesByUsernameQuery, new String[] { username }, new RowMapper() {
    public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
      String roleName = AdvanzUserServiceImpl.this.getRolePrefix() + rs.getString(3);

      return new SimpleGrantedAuthority(roleName);
    }
  });
}

public List<UserMenuOptions> getUserMenuOptionsList(String userRole)
{
  	
  return getJdbcTemplate().query(this.userMenuOptionsQuery, new String[] { userRole }, new RowMapper() {
    public UserMenuOptions mapRow(ResultSet rs, int rowNum) throws SQLException {
    	UserMenuOptions menuOptions =  new UserMenuOptions();
    	menuOptions.setModuleName(rs.getString(1));
    	menuOptions.setSubModuleName(rs.getString(2));
    	menuOptions.setMenuName(rs.getString(3));
    	menuOptions.setVisibleFlag(rs.getBoolean(4));
    	menuOptions.setAddFlag(rs.getBoolean(5));
    	menuOptions.setEditFlag(rs.getBoolean(6));
    	menuOptions.setDeleteFlag(rs.getBoolean(7));
      return menuOptions;
    }
  });
}


protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery, List<GrantedAuthority> combinedAuthorities)
{
  String returnUsername = userFromUserQuery.getUsername();

  if (!this.usernameBasedPrimaryKey) {
    returnUsername = username;
  }

  return new User(returnUsername, userFromUserQuery.getPassword(), userFromUserQuery.isEnabled(), true, true, true, combinedAuthorities);
}

public void setAuthoritiesByUsernameQuery(String queryString)
{
  this.authoritiesByUsernameQuery = queryString;
}

protected String getAuthoritiesByUsernameQuery() {
  return this.authoritiesByUsernameQuery;
}

public void setGroupAuthoritiesByUsernameQuery(String queryString)
{
  this.groupAuthoritiesByUsernameQuery = queryString;
}

public void setRolePrefix(String rolePrefix)
{
  this.rolePrefix = rolePrefix;
}

protected String getRolePrefix() {
  return this.rolePrefix;
}

public void setUsernameBasedPrimaryKey(boolean usernameBasedPrimaryKey)
{
  this.usernameBasedPrimaryKey = usernameBasedPrimaryKey;
}

protected boolean isUsernameBasedPrimaryKey() {
  return this.usernameBasedPrimaryKey;
}

public void setUsersByUsernameQuery(String usersByUsernameQueryString)
{
  this.usersByUsernameQuery = usersByUsernameQueryString;
}

protected boolean getEnableAuthorities() {
  return this.enableAuthorities;
}

public void setEnableAuthorities(boolean enableAuthorities)
{
  this.enableAuthorities = enableAuthorities;
}

protected boolean getEnableGroups() {
  return this.enableGroups;
}

public void setEnableGroups(boolean enableGroups)
{
  this.enableGroups = enableGroups;
}
}
