--animation
function async(func)
	task_id+=1
	cr=cocreate(func)
	routines[task_id]={thread=cr}
	return task_id
end

function wait(frames)
	for i=1,frames do
		yield()
	end
end

function animate(obj,k,v1,f,e)
	local v0=obj[k]
	for i=1,f do
	 obj[k]=lerp(v0,v1,i/f,e) 
	 yield()
	end
end
function a_animate(obj,k,v1,f,e)
	return async(function()
	 animate(obj,k,v1,f,e)
	end)
end

function task(func,stop_cond,rate)
	local cycles=0
	local age=0
 rate = rate or 1
	return async(function()
		if (not stop_cond()) yield()
		while not stop_cond() do
			if (age%rate==0) then
				func()
				cycles+=1
			end
			age+=1
			yield({cycles})
		end
	end)
end

function sized_task(func,times,rate)
	local age=0
 rate = rate or 1
	return async(function()
		for i=0,times do
			if (age%rate==0) func()
			age+=1
			yield({progress=i/times})
		end
	end)
end

function lerp(a,b,t,e)
	local easing = e or e_linear
	return easing(a,b,t)
end

function e_linear(a,b,t)
	return a+(b-a)*t
end

--counter test task
function _counter()
	local acc=0
	local finished=false
	return {
		tick=function() acc+=7 end,
		get=function() return acc end,
		finished=function()
			return acc>=120 or arb_flag
		end
	}
end