package hob.psd.todo.dto;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class UserDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	private int userId;

	
	@Column(name = "firstName",nullable=false)
	private String firstName;
	
	@Column(name = "lastName",nullable=false)
	private String lastName;
	
	@Column(name = "userName",unique=true,nullable=false)
	private String userName;
	
	@Column(name = "password",nullable=false)
	private String password;
	
	@Column(name= "email" , nullable=false)
	private String email;
	
	@Column(name="level", nullable=false)
	private String level;
	
	@Column(name="aboutUser")
	private String aboutUser;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable( name = "user_tasks",joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = { @JoinColumn(name = "taskId") })
    private List<TaskDTO> tasks = new ArrayList<TaskDTO>();

    @OneToMany
    private List<TaskDTO> ownedTasks = new ArrayList<TaskDTO>();

    private Integer profilePicId;

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAboutUser() {
		return aboutUser;
	}

	public void setAboutUser(String aboutUser) {
		this.aboutUser = aboutUser;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    public List<TaskDTO> getOwnedTasks() {
        return ownedTasks;
    }

    public void setOwnedTasks(List<TaskDTO> ownedTasks) {
        this.ownedTasks = ownedTasks;
    }

    public Integer getProfilePicId() {
        return profilePicId;
    }

    public void setProfilePicId(Integer profilePicId) {
        this.profilePicId = profilePicId;
    }
}
