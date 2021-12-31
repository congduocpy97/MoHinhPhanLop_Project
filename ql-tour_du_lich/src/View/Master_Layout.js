import React from 'react';
import '../index.css';
import { Breadcrumb, Layout, Menu } from 'antd';
import { Menu_List } from '../Support/Constant';
import { Link } from 'react-router-dom';

const { Header, Footer, Content, Sider } = Layout;
const { SubMenu } = Menu;

class MasterLayout extends React.Component {

  state = {
    collapsed: false,
  };

  onCollapse = collapsed => {
    console.log(collapsed);
    this.setState({ collapsed });
  };


  render() {
    const { collapsed } = this.state;
    const component = this.props.component
    return (
      <Layout style={{ minHeight: '100vh' }}>
        <Sider collapsible collapsed={collapsed} onCollapse={this.onCollapse}>
          {/* <div className="logo" /> */}
          <Menu theme="dark" defaultOpenKeys={[this.props.menu]}  defaultSelectedKeys={[this.props.subMenu]} mode="inline">
            {Menu_List.map(m => {
              const item = m.view.map(v => {
                return(
                <Menu.Item key={v.key}>
                  <Link to={`/${v.key.toLocaleLowerCase()}`}>
                    {v.name}
                  </Link>
                </Menu.Item>
                )
              })
              return (
                <SubMenu key={m.name} icon={m.icon} title={m.name}>
                  {item}
                </SubMenu>
              )
            })}
          </Menu>
        </Sider>
        <Layout className="site-layout">
          <Header className="site-layout-background" style={{ padding: 0 }} />
          <Content style={{ margin: '0 16px' }}>
            <Breadcrumb style={{ margin: '16px 0' }}>
              <Breadcrumb.Item>{this.props.name}</Breadcrumb.Item>
            </Breadcrumb>
            {component}
          </Content>
          <Footer style={{ textAlign: 'center' }}>Mô Hình Phân Lớp</Footer>
        </Layout>
      </Layout>
    );
  }
}

export default MasterLayout;