import Link from "next/link";
import Image from "next/image";
import logo from "../public/logo.png";
import navStyle from "../styles/Nav.module.css";

const Nav = () => {
  return (
    <nav className={navStyle.navbar}>
      <Image alt="Excerpt Logo" src={logo} className={navStyle.logo}/>
      <ul>
        <li className={navStyle.navItems}>
          <Link href="/">New Excerpt</Link>
        </li>
        <li className={navStyle.navItems}>
          <Link href="/excerpts">Your Excerpts</Link>
        </li>
      </ul>
    </nav>
  );
};

export default Nav;
