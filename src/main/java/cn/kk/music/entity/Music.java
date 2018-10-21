package cn.kk.music.entity;

import java.io.Serializable;

public class Music implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5166709336528215243L;
	private int id;
	private String musicName;
	private String musicPath;
	private String musicInfo;
	private String musicLRC;
	@Override
	public String toString() {
		return "Music [id=" + id + ", musicName=" + musicName + ", musicPath=" + musicPath + ", musicInfo=" + musicInfo
				+ ", musicLRC=" + musicLRC + "]";
	}
	public Music() {
	}
	public Music(int id, String musicName, String musicPath, String musicInfo, String musicLRC) {
		super();
		this.id = id;
		this.musicName = musicName;
		this.musicPath = musicPath;
		this.musicInfo = musicInfo;
		this.musicLRC = musicLRC;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public String getMusicPath() {
		return musicPath;
	}
	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}
	public String getMusicInfo() {
		return musicInfo;
	}
	public void setMusicInfo(String musicInfo) {
		this.musicInfo = musicInfo;
	}
	public String getMusicLRC() {
		return musicLRC;
	}
	public void setMusicLRC(String musicLRC) {
		this.musicLRC = musicLRC;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Music other = (Music) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
