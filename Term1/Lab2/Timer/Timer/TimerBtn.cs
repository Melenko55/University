using System;

namespace Timer
{
    internal class TimerBtn
    {
        TimeSpan timeLeft;
        bool isStopped;

        public TimerBtn(String settup)
        {
            String[] separator = { ":" };
            String[] userData = settup.Split(separator, StringSplitOptions.RemoveEmptyEntries);
            int seconds = Convert.ToInt32(userData[0]) * 3600 + Convert.ToInt32(userData[1]) * 60 + Convert.ToInt32(userData[2]);
            timeLeft = TimeSpan.FromSeconds(seconds);
            isStopped = false;
        }
        
        public void update()
        {
            if (timeLeft == TimeSpan.Zero) isStopped = true;
            if (!isStopped) timeLeft = timeLeft.Add(TimeSpan.FromSeconds(-1));
        }

        public TimeSpan getTime()
        {
            return timeLeft;
        }

        public void toggleTimer()
        {
            isStopped = !isStopped;
        }
        public bool IsStopped()
        {
            return isStopped;
        }
    }
}