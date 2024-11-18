import logoImage from "../img/logoEventus.png"

export function Footer() {
  return (
    <footer className="footerPages">
      <a href="#" target="_blank" rel="noopener noreferrer">
        <img className="footerImage"
          src={logoImage}
          alt="J Richard Hill"/>
      </a>
      <br />
      <a className="footerEmail" href="mailto:eventusmaisprati@gmail.com">
        eventusmaisprati@gmail.com
      </a>
      <br />
      <div className="footerSocials">
        <a
          href="https://www.instagram.com/j.richard.hill.rick.hill/"
          target="_blank"
          rel="noopener noreferrer"
        >
          <img className="footerIcon"
            src="images/instagram.png"
            alt="Instagram"
          />
        </a>
        <a
          href="https://twitter.com/jrichardhillco"
          target="_blank"
          rel="noopener noreferrer"
        >
          <img className="footerIcon"
            src="images/twitter.png"
            alt="Twitter"
          />
        </a>
        <a
          href="https://www.linkedin.com/in/rick-hill-880aa311/"
          target="_blank"
          rel="noopener noreferrer"
        >
          <img className="footerIcon"
            src="images/linked_in.png"
            alt="LinkedIn"
          />
        </a>
      </div>
    </footer>
  );
};